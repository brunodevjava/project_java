package com.dev.project.repository;

import com.dev.project.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("FROM User u WHERE u.email=:email AND u.status=true")
    Optional<User> findByEmailAndStatusTrue(@Param("email") String email);

    List<User> findByStatusTrue();

    Optional<User> findByTokenAndStatusTrue(String token);

    @Query(value = "select us.* from  users us\n" +
            "where  us.access_type like ?1 and us.status = 1", nativeQuery = true)
    List<User> findAllUserResponsible(String accessType);

    @Query(value = "select u.* from users u  left join workflow_approve wa on u.id = wa.user_id\n" +
            "where u.profile_id = ?1", nativeQuery = true)
    User findByRoleId(Long roleId);

    @Query(value = "select u.* from users u where u.id=?1", nativeQuery = true)
    List<User> findAllUsers(Long roleId);
}
