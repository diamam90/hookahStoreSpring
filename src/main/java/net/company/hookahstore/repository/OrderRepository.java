package net.company.hookahstore.repository;

import net.company.hookahstore.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends PagingAndSortingRepository<Order,Long> {
    Order findOrderById(Long id);
    Page<Order> findAllByIdAccount(@Param("id_account")Long idAccount, Pageable pageable);
}
