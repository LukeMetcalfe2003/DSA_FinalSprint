package com.example.demo.BinarySearchTree;
import com.example.demo.BinarySearchTree.TreeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TreeRecordRepo extends JpaRepository<TreeRecord, Long> {
    List<TreeRecord> findAll();
}
