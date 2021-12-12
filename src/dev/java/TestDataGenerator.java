import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class TestDataGenerator {
    private static String JDBC_URL = "jdbc:postgresql://localhost:5432/hookahstore";
    private static String JDBC_USERNAME = "hookahstore";
    private static String JDBC_PASSWORD = "34525";

    private static String IMG_PATH = "external/test-data/";
    private static String MEDIA_PATH = "src/main/webapp/media/";
    private static Random RANDOM = new Random();

    static final String[] HOOKAH_PRODUCER = {"DSH", "Alpha hookah", "AEON", "Mamay", "Hoob", "HookahTree", "Japona", "MattPear"};
    static final String[] TABACCO_PRODUCER = {"Daily Hookah", "DUFT", "Burn", "Darkside", "Bonche", "Must Have", "Element", "Tangiers"};
    static final String[] COAL_PRODUCER = {"Big Maks", "Darkside", "Cocoloco", "Oasis", "Panda"};
    static final String[] BOWL_PRODUCER = {"Bonche", "Cosmo Bowl", "Element", "Smokelab", "Werkbund", "Telamon", "Vintage", "Облако"};
    static final String[] TONG_PRODUCER = {"50 Clouds", "Werkbund", "Blade", "Maklaud", "Target", "Alpha Hookah"};
    static final String[] HEATKEEPER_PRODUCER = {"Kaloud", "Heat keeper", "Aplha", "Unknown"};

    static final String[] HOOKAH_DESC = {"Высота: {40,60} см", "Комплектация: шахта, блюдце, [колба,шланг,чаша]"};
    static final String[] TABACCO_DESC = {"Вес: [20,25,30,50,100] г", "Вкус: [Raspberry,Jam, Grape,Mint, Cane mint, Passion fruit,Lemon,Melon,Watermelon,Pear,Apple,Vanilla,Cheesecake,Pumpkin pie,Pinkman]"};
    static final String[] COAL_DESC = {"Размер: [22,25] мм", "Качественный кокосовый уголь. Не обладает посторонним запахом при розжиге, мало пепла."};
    static final String[] BOWL_DESC = {"Материал: [глина,керамика]", "Вместимость: {10,25} г"};
    static final String[] TONG_DESC = {"Длина: {15,30} см", "Цвет: [Deep blue,Shiny,Stainless,Gold,Dark brownie,Black Matte]", "Щипцы предназначены для работы с углём, чтобы класть горячий уголь на фольгу или в Колауд"};
    static final String[] HEATKEEPER_DESC = {"Устройство позволяет без труда регулировать передачу тепла от угля к табаку и оптимизировать расход угля и табака при курении кальяна."};

    public static void main(String[] args) throws Exception {
        List<Category> categoryList = loadCategories();
        List<Producer> producerList = getProducers(categoryList);
        clearMediaDir();
        try (Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            c.setAutoCommit(false);
            clearDB(c);
            Map<String, Integer> categoryIdMap = insertCategories(c, categoryList);
            Map<String, Integer> producerIdMap = insertProducers(c, producerList);

            List<Product> productsList = generateProducts(categoryList, categoryIdMap, producerIdMap);
            insertProducts(c, categoryList, producerList, categoryIdMap, producerIdMap);
            c.commit();
        } catch (SQLException e) {
            if (e.getNextException() != null) {
                e.getNextException().printStackTrace();
            } else {
                e.printStackTrace();
            }
        }

    }

    private static List<Category> loadCategories() {
        List<Category> list = new ArrayList<>();
        File categoryDir = new File(IMG_PATH);
        for (String categoryName : categoryDir.list()) {
            String ruName;
            switch (categoryName) {
                case "bowl": {
                    ruName = "Чашки";
                    break;
                }
                case "hookah": {
                    ruName = "Кальяны";
                    break;
                }
                case "coal": {
                    ruName = "Уголь";
                    break;
                }
                case "heatkeeper": {
                    ruName = "Хиткиперы";
                    break;
                }
                case "tong": {
                    ruName = "Щипцы";
                    break;
                }
                default:
                case "tabacco": {
                    ruName = "Табаки";
                }
            }
            File imgDir = new File(IMG_PATH + categoryName);
            File[] files = imgDir.listFiles();
            list.add(new Category(categoryName, ruName, files[RANDOM.nextInt(files.length)]));
        }
        Collections.sort(list);
        return list;
    }

    private static List<Producer> getProducers(List<Category> categories) {
        Map<String, Producer> map = new HashMap<>();
        for (Category category : categories) {
            for (Producer producer : category.producerList) {
                Producer p = map.get(producer.name);
                if (p == null) {
                    map.put(producer.name, producer);
                } else {
                    map.put(producer.name, new Producer(p.name, p.productCount + producer.productCount));
                }
            }
        }
        return new ArrayList<>(map.values());
    }

    private static void clearDB(Connection c) throws SQLException {
        try (Statement st = c.createStatement()) {
            st.executeUpdate("delete from order_item");
            st.executeUpdate("delete from \"order\"");
            st.executeUpdate("delete from account");
            st.executeUpdate("delete from product");
            st.executeUpdate("delete from category");
            st.executeUpdate("delete from producer");
        }
        System.out.println("Db cleared");
    }

    private static void clearMediaDir() {
        File dir = new File(MEDIA_PATH);
        int count = 0;
        for (File file : dir.listFiles()) {
            file.delete();
            count++;
        }
        System.out.println("Removed " + count + " image files");
    }

    private static Map<String, Integer> insertProducers(Connection c, List<Producer> producers) throws Exception {
        Map<String, Integer> idMap = new HashMap<>();
        int i = 1;
        try (PreparedStatement ps = c.prepareStatement("insert into producer values(?,?,?)")) {
            for (Producer p : producers) {
                idMap.put(p.name, i);
                ps.setInt(1, i++);
                ps.setString(2, p.name);
                ps.setInt(3, p.productCount);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        System.out.println("Inserted " + producers.size() + " producers");
        return idMap;
    }

    private static Map<String, Integer> insertCategories(Connection c, List<Category> categories) throws Exception {
        Map<String, Integer> idMap = new HashMap<>();
        int i = 1;
        try (PreparedStatement ps = c.prepareStatement("insert into category values(?,?,?,?,?)")) {
            for (Category category : categories) {
                idMap.put(category.name, i);
                ps.setInt(1, i++);
                ps.setString(2, category.name);
                ps.setInt(3, category.getProductCount());
                ps.setString(4, "/" + capitalize(category.name).toLowerCase());
                ps.setString(5, category.ruName);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        System.out.println("Inserted " + categories.size() + " categories");
        return idMap;
    }

    private static String capitalize(String name) {
        if (name == null) {
            return null;
        } else if (name.length() == 1) {
            return name.toUpperCase();
        } else {
            return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
        }

    }

    private static void insertProducts(Connection c, List<Category> categories, List<Producer> producers, Map<String, Integer> producerIdMap, Map<String, Integer> categoryIdMap) throws Exception {
        List<Product> products = generateProducts(categories, producerIdMap, categoryIdMap);
        int i = 33485;
        try (PreparedStatement ps = c.prepareStatement("insert into product values(?,?,?,?,?,?,?,?,?)")) {
            for (Product product : products) {
                ps.setInt(1, i);
                ps.setString(2, product.name);
                ps.setInt(3, product.idProducer);
                ps.setInt(4, product.idCategory);
                ps.setString(5, product.imageLinkLarge);
                ps.setString(6, generateDescription(categories.get(product.idCategory - 1)));
                ps.setInt(7, (RANDOM.nextInt(200) * 10) + 1000);
                ps.setInt(8, product.count);
                ps.setString(9, product.imageLinkSmall);
                ps.addBatch();
                i++;
            }
            ps.executeBatch();
        }
        System.out.println("Inserted " + products.size() + " products");
    }

    public static List<Product> generateProducts(List<Category> categories, Map<String, Integer> categoryIdMap, Map<String, Integer> producerIdMap) throws Exception {
        List<Product> products = new ArrayList<>();
        for (Category category : categories) {
            for (Producer producer : category.producerList) {
                int idCategory = categoryIdMap.get(category.name);
                int idProducer = producerIdMap.get(producer.name);
                for (int i = 0; i < producer.productCount; i++) {
                    String name = generateProductName(producer.name);
                    String imageLinkLarge = generateImageLink(category, producer);
                    String imageLinkSmall = imageLinkLarge.replace(".jpg", "").concat("-sm.jpg");
                    Thumbnails.of(new File(MEDIA_PATH.replace("/media", "") + imageLinkLarge)).size(400, 400).toFile(new File(MEDIA_PATH.replace("/media", "") + imageLinkSmall));
                    products.add(new Product(name, idCategory, idProducer, category.name, imageLinkLarge, imageLinkSmall));
                }
            }
        }
        Collections.shuffle(products);
        return products;
    }

    public static String generateProductName(String producerName) {
        StringBuilder productCode = new StringBuilder();
        for (int i = RANDOM.nextInt(2) + 1; i >= 0; i--) {
            productCode.append((char) ('A' + RANDOM.nextInt(22)));
        }
        for (int i = RANDOM.nextInt(5) + 3; i >= 0; i--) {
            productCode.append(RANDOM.nextInt(10) + 1);
        }
        return producerName + " " + productCode;
    }

    public static String generateImageLink(Category category, Producer producer) throws IOException {
        String productImageName = getImageFileName(category);
        File[] imagesPackage = new File(IMG_PATH + category.name).listFiles();
        List<File> fileList = new ArrayList<>();
        for (File f : imagesPackage) {
            if (f.getName().contains(producer.name)) {
                fileList.add(f);
            }
        }
        File from;
        if (fileList.isEmpty()) {
            from = imagesPackage[RANDOM.nextInt(imagesPackage.length)];
        } else {
            from = fileList.get(RANDOM.nextInt(fileList.size()));
        }
        File dest = new File(MEDIA_PATH + productImageName);
        Files.copy(Paths.get(from.toURI()), Paths.get(dest.toURI()));
        return "media/" + productImageName;
    }

    public static String generateDescription(Category category) {
        String fieldName = category.name.toUpperCase() + "_DESC";
        StringBuilder description = new StringBuilder();
        try {
            Field field = TestDataGenerator.class.getDeclaredField(fieldName);
            String data[] = (String[]) field.get(TestDataGenerator.class);
            for (int i = 0; i < data.length; i++) {
                if (i == data.length - 1) {
                    description.append(handle(data[i]));
                } else {
                    description.append(handle(data[i])).append(". ");
                }
            }
        } catch (NoSuchFieldException e) {
            System.err.println("Field not found: " + e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
        return description.toString();
    }

    private static String handle(String str) {
        StringBuilder result = new StringBuilder();
        if (str.contains("{") || str.contains("}")) {
            int begin = str.indexOf("{");
            int end = str.indexOf("}");
            String s = str.substring(begin + 1, end);
            String[] data = s.split(",");
            int min = Integer.parseInt(data[0]);
            int max = Integer.parseInt(data[1]);
            result.append(str.substring(0, begin)).append(RANDOM.nextInt(max - min) + min).append(str.substring(end + 1));
            return result.toString();
        } else if (str.contains("[") || str.contains("]")) {
            int begin = str.indexOf("[");
            int end = str.indexOf("]");
            String s = str.substring(begin + 1, end);
            String[] data = s.split(",");
            result.append(str.substring(0, begin)).append(data[RANDOM.nextInt(data.length)]).append(str.substring(end + 1));
            return result.toString();
        } else {
            return str;
        }
    }

    private static String getImageFileName(Category category) {
        return UUID.randomUUID().toString().replace("-", "_") + ".jpg";
    }

    private static class Category implements Comparable<Category> {
        final String name;
        final File imageFile;
        final List<Producer> producerList;
        final String ruName;

        public Category(String name, String ruName, File imageFile) {
            this.name = name;
            this.imageFile = imageFile;
            this.ruName = ruName;
            this.producerList = Collections.unmodifiableList(createProducerList());
        }

        public List<Producer> createProducerList() {
            String staticFieldName = name.toUpperCase().replace("-", "_") + "_PRODUCER";
            try {
                Field field = TestDataGenerator.class.getDeclaredField(staticFieldName);
                String[] data = (String[]) field.get(TestDataGenerator.class);
                List<Producer> result = new ArrayList<>();
                for (String producer : data) {
                    result.add(new Producer(producer));
                }
                return result;
            } catch (NoSuchFieldException e) {
                System.err.println(staticFieldName + " field not found for category " + name);
                return Collections.emptyList();
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }
        }

        int getProductCount() {
            int res = 0;
            for (Producer producer : producerList) {
                res += producer.productCount;
            }
            return res;
        }

        @Override
        public int compareTo(Category o) {
            return name.compareToIgnoreCase(o.name);
        }

        @Override
        public String toString() {
            return "Category{" +
                    "name='" + name + '\'' +
                    ", ruName=" + ruName +
                    ", imageFile=" + imageFile +
                    ", producerList=" + producerList +
                    '}';
        }
    }

    private static class Producer {
        final String name;
        final int productCount;

        public Producer(String name) {
            this(name, RANDOM.nextInt(25) + 2);
        }

        public Producer(String name, int productCount) {
            this.name = name;
            this.productCount = productCount;
        }

        @Override
        public String toString() {
            return "Producer{" +
                    "name='" + name + '\'' +
                    ", productCount=" + productCount +
                    '}';
        }
    }

    private static class Product {
        final String name;
        final int idCategory;
        final int idProducer;
        final String category;
        final String imageLinkLarge;
        final String imageLinkSmall;
        final int count;

        public Product(String name, int idCategory, int idProducer, String category, String imageLinkLarge, String imageLinkSmall) {
            this.idCategory = idCategory;
            this.category = category;
            this.idProducer = idProducer;
            this.name = name;
            this.imageLinkLarge = imageLinkLarge;
            this.imageLinkSmall = imageLinkSmall;
            this.count = RANDOM.nextInt(1000);
        }
    }

}
