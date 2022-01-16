package net.company.hookahstore.service.impl;

import net.company.hookahstore.entity.Category;
import net.company.hookahstore.entity.Producer;
import net.company.hookahstore.entity.Product;


import net.company.hookahstore.exception.InternalServerErrorException;
import net.company.hookahstore.repository.CategoryRepository;
import net.company.hookahstore.repository.ProducerRepository;
import net.company.hookahstore.repository.ProductRepository;
import net.company.hookahstore.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProducerRepository producerRepository;


    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl() {
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }


    @Override
    public Page<Product> listProductByCategoryAndProducer(HttpServletRequest req, Pageable pageable) {
        if (req.getParameter("cat") == null) {
            throw new InternalServerErrorException("/categories without params");
        }
        Long categoryId = Long.parseLong(req.getParameter("cat"));
        if (req.getParameter("prod") == null) {

            return productRepository.findAllByCategory_Id(categoryId, pageable);
        }
        Long producerId = Long.parseLong(req.getParameter("prod"));
        return productRepository.findAllByCategory_IdAndProducer_Id(categoryId, producerId, pageable);
    }

    @Override
    public Page<Product> listProductBySearch(HttpServletRequest req, Pageable pageable) {
        if (!req.getParameterNames().hasMoreElements()) {
            throw new InternalServerErrorException("search request without params");
        }
        String param = "%" + req.getParameter("query") + "%";
        if (req.getParameter("cat").equals("all")) {
            return productRepository.findAllProductsBySearchQuery(param, param, pageable);
        } else {
            return productRepository.findAllProductsBySearchQueryWithChosenCategory(Long.parseLong(req.getParameter("cat")), param, param, pageable);
        }
    }

    @Override
    public Map<Category, List<Producer>> getProducersByCategoryMap(List<Category> categories) {
        Map<Category, List<Producer>> result = new HashMap<>();
        for (Category category : categories) {
            List<Producer> producerList = producerRepository.getProducersByCategory(category.getId());
            result.put(category, producerList);
        }
        return result;
    }
/*

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

   */
}
