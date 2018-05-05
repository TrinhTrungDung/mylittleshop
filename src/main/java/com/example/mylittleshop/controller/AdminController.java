package com.example.mylittleshop.controller;
import com.example.mylittleshop.entity.*;
import com.example.mylittleshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Secured("ROLE_MANAGER")
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ImportRepository importRepository;

    @GetMapping(value = "shops")
    public String shops(Model model) {
        model.addAttribute("shops", shopRepository.findAll());
        return "shop_list.html";
    }

    @GetMapping(value = "employees")
    public String employees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "emp_list.html";
    }

    @GetMapping(value = "items")
    public String tasks(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "item_list.html";
    }

    @GetMapping(value = "shops/{id}")
    public String shop(@PathVariable("id") Long id, Model model) {
        Shop shop = shopRepository.findById(id).get();

        List<Employee> employees = employeeRepository.findByShop(shop);
        List<Sale> sales = saleRepository.findByShop(shop);
        List<Inventory> inventories = inventoryRepository.findByIdShop(shop);
        List<Import> imports = importRepository.findByShop(shop);
        model.addAttribute("employees", employees);
        model.addAttribute("sales", sales);
        model.addAttribute("inventories", inventories);
        model.addAttribute("imports", imports);
        return "shop.html";
    }


}
