package com.horetski.example.repo;

import com.horetski.example.domain.Ordering;
import com.horetski.example.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderingRepo extends CrudRepository<Ordering, Long> {
    public Iterable<Ordering> findAllByUser(User user);

}
