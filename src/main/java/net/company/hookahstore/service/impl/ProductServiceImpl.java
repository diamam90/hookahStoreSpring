package net.company.hookahstore.service.impl;

import net.company.hookahstore.configuration.JPAConfig;
import net.company.hookahstore.entity.Category;
import net.company.hookahstore.entity.Producer;
import net.company.hookahstore.entity.Product;


import net.company.hookahstore.repository.ProductRepository;
import net.company.hookahstore.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private  ProductRepository productRepository;


    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(){}

     @Override
    public List<Product> listAllProduct(int page, int limit) {
        return productRepository.findAllProduct();
    }

    @Override
    public int countAllProduct() {
        return 0;
    }

    @Override
    public List<Product> listProductByCategory(HttpServletRequest req, int page, int limit) {
        return null;
    }

    @Override
    public int countProductByAside(HttpServletRequest req) {
        return 0;
    }

    @Override
    public List<Category> listAllCategories() {
        return null;
    }

    @Override
    public List<Producer> listAllProducers() {
        return null;
    }

    @Override
    public List<Product> listProductBySearch(String category, String searchQuery) {
        return null;
    }

    @Override
    public int countProductBySearch(String category, String searchQuery) {
        return 0;
    }

    @Override
    public Map<Category, List<Producer>> mapProducerByCategory() {
        return null;
    }
    /*
    @Override
    public List<Product> listAllProduct(int page, int limit) {
        try (Connection c = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            return JDBCUtils.select(c, "select p.*, c.ru_name as category, pr.name as producer from product p,category c,producer pr " +
                    "where p.id_category = c.id and p.id_producer = pr.id limit ? offset ?", productsResultSetHandler, limit, offset);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute query: " + e.getMessage(), e);
        }
    }

    @Override
    public int countAllProduct() {
        try (Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "select count(*) from product", countResultSetHandler);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute query: " + e.getMessage(), e);
        }
    }


    @Override
    public List<Product> listProductByCategory(HttpServletRequest req, int page, int limit) {
        int offset = (page - 1) * limit;
        try (Connection c = dataSource.getConnection()) {
            StringBuilder query=buildQuery(req,"select p.*, c.ru_name as category, pr.name as producer from product p, category c, producer pr where " +
                    "p.id_category=c.id and p.id_producer=pr.id");
            query.append(" limit ? offset ?");
            LOGGER.debug("execute category ={}, producer={}", req.getParameterValues("cat"),req.getParameterValues("prod"));
            return JDBCUtils.select(c,query.toString(),productsResultSetHandler,limit,offset);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute query: " + e.getMessage(), e);
        }
    }

    public StringBuilder buildQuery(HttpServletRequest req,String sql){
        StringBuilder query = new StringBuilder(sql);
        Map<String,String[]> parameterMap = req.getParameterMap();
        for (Map.Entry entry:parameterMap.entrySet()){
            if (entry.getKey().equals("cat")){
                for (String s: (String[])entry.getValue()){
                    query.append(" and c.id=").append(Integer.parseInt(s));
                }
            }else if (entry.getKey().equals("prod")){
                for (String s: (String[])entry.getValue()){
                    query.append(" and pr.id=").append(Integer.parseInt(s));
                }
            }
        }
        return query;
    }
    @Override
    public int countProductByAside(HttpServletRequest req) {
        try (Connection c = dataSource.getConnection()) {
            StringBuilder query=buildQuery(req,"select count(*) from product p, category c, producer pr where " +
                    "p.id_category=c.id and p.id_producer=pr.id");
            LOGGER.debug("execute category ={}, producer={}", req.getParameterValues("cat"),req.getParameterValues("prod"));
            return JDBCUtils.select(c,query.toString(),countResultSetHandler);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute query: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Category> listAllCategories() {
        try (Connection c = dataSource.getConnection()) {
            List<Category> categories = JDBCUtils.select(c, "select * from category", categoriesResultSetHandler);
            return categories;
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute query:" + e.getMessage(), e);
        }
    }

    @Override
    public List<Producer> listAllProducers() {
        try (Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "select * from producer", producersResultSetHandler);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute query:" + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> listProductBySearch(String category,String searchQuery) {
        try (Connection c = dataSource.getConnection()){
            String param = "%"+searchQuery+"%";
            if (category.equals("all")) {
                return JDBCUtils.select(c, "select p.*, c.ru_name as category, pr.name as producer from product p, category c, producer pr " +
                        "where p.id_category=c.id and p.id_producer=pr.id and (c.name ilike ? or pr.name ilike ? or p.name ilike ?)", productsResultSetHandler, param, param, param);
            } else {
                int categoryId = Integer.parseInt(category);
                return JDBCUtils.select(c, "select p.*, c.ru_name as category, pr.name as producer from product p, category c, producer pr " +
                        "where p.id_category=c.id and p.id_producer=pr.id and (c.name ilike ? or pr.name ilike ? or p.name ilike ?) and c.id = ?", productsResultSetHandler, param, param, param, categoryId);
            }

        }catch (SQLException e){
            throw new InternalServerErrorException(e.getMessage(),e);
        }
    }

    @Override
    public int countProductBySearch(String category,String searchQuery) {
        try (Connection c = dataSource.getConnection()){
            String param = "%"+searchQuery+"%";
            if (category.equals("all")) {
                return JDBCUtils.select(c, "select count(*) from product p, category c, producer pr " +
                        "where p.id_category=c.id and p.id_producer=pr.id and (c.name ilike ? or pr.name ilike ? or p.name ilike ?)", countResultSetHandler, param, param, param);
            } else {
                int categoryId = Integer.parseInt(category);
                return JDBCUtils.select(c, "select count(*) from product p, category c, producer pr " +
                        "where p.id_category=c.id and p.id_producer=pr.id and (c.name ilike ? or pr.name ilike ? or p.name ilike ?) and c.id=?", countResultSetHandler, param, param, param,categoryId);
            }
        }catch (SQLException e){
            throw new InternalServerErrorException(e.getMessage(),e);
        }
    }

    public Map<Category, List<Producer>> mapProducerByCategory() {
        try (Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "select distinct c.*, pr.* from category c, producer pr, product p where p.id_category=c.id and p.id_producer=pr.id",
                    ResultSetHandlerFactory.mapResultSetHandler);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute query:" + e.getMessage(), e);
        }
    }*/
}
