package com.example.demo.BinarySearchTree;
import com.example.demo.BinarySearchTree.TreeRecord;
import com.example.demo.BinarySearchTree.TreeRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Controller
public class TreeController {

    @Autowired
    private TreeRecordRepository treeRecordRepository;

    private BinarySearchTree bst = new BinarySearchTree();

    @GetMapping("/enter-numbers")
    public String showInputForm() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    @ResponseBody
    public java.util.Map<String, Object> processNumbersJson(@RequestParam("numbers") String numbers) {
        BinarySearchTree bstJson = new BinarySearchTree();
        String[] numArray = numbers.split(",");
        for (String num : numArray) {
            bstJson.insert(Integer.parseInt(num.trim()));
        }

        TreeRecord treeRecord = new TreeRecord();
        treeRecord.setInputNumbers(numbers);

        treeRecord.setTreeStructure(bstJson.toJson());
        treeRecordRepository.save(treeRecord);

        return bstJson.toJsonRec(bstJson.getRoot());
    }


    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        List<TreeRecord> records = treeRecordRepository.findAll();
        model.addAttribute("records", records);
        return "previous-trees"; }
}
