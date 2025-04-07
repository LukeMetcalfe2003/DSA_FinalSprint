package com.example.demo.BinarySearchTree;
import com.example.demo.BinarySearchTree.TreeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@Controller
public class TreeController {

    @Autowired
    private TreeRecordRepo treeRecordRepo;

    private BinarySearchTree binarySearchTree = new BinarySearchTree();

    @GetMapping("/enter-numbers")
    public String showInputForm(){
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("numbers") String numbers, Model model){
        String[] numberArray = numbers.split(",");
        List<Integer> numberList = new ArrayList<>();

        for(String number : numberArray){
            numberList.add(Integer.parseInt(number.trim()));
            binarySearchTree.insert(Integer.parseInt(number.trim()));
        }

        TreeRecord treeRecord = new TreeRecord();
        treeRecord.setInputNums(numbers);
        treeRecord.setTreeStructure(binarySearchTree.inorderTraversal().toString());
        treeRecordRepo.save(treeRecord);

        model.addAttribute("treeStructure", binarySearchTree.inorderTraversal());
        return "tree-result";
    }

    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model){
        List<TreeRecord> records = treeRecordRepo.findAll();
        model.addAttribute("records", records);
        return "previous-trees";
    }
}
