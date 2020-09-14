package com.techframe.ecomerce.service;

import com.techframe.ecomerce.model.Category;
import com.techframe.ecomerce.model.Subcategory;
import com.techframe.ecomerce.model.User;
import com.techframe.ecomerce.repository.SubcategoryRepository;
import com.techframe.ecomerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryServiceM implements SubcategoryService {

    private SubcategoryRepository usersRepository;

    @Autowired
    public void setUsersRepositoryRepository(SubcategoryRepository usersRepository) {

        this.usersRepository = usersRepository;
    }


    @Override
    public List<Subcategory> listAllSubcategories(){

        return usersRepository.findAll();
    }

    @Override
    public Subcategory getSubcategoryByName(String username) {
        return usersRepository.findSubcategoryByName(username);
    }
    @Override
    public Subcategory saveSubcategory(Subcategory product) {

        return usersRepository.save(product);
    }

    @Override
    public Subcategory getSubcategoryById(Integer id) {
       return usersRepository.findById(id).orElse(null);
    }



    public void deleteSubcategory(Integer id) {
        usersRepository.deleteById(id);
    }
}
