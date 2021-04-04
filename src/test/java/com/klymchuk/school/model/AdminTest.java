//package com.klymchuk.school.model;
//
//import com.klymchuk.school.repo.AdminRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//@Slf4j
//public class AdminTest {
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    @Test
//    void test(){
//        Assertions.assertEquals(1, 1);
//    }
//
//    interface Pars{
//
//    }
//    static class Par implements Pars{
//        int value;
//
//        public Par(int value) {
//            this.value = value;
//        }
//
//        @Override
//        public String toString() {
//            return "Par{" +
//                    "value=" + value +
//                    '}';
//        }
//    }
//
//    @Test
//    void getAdminFromDataBase(){
//        Admin admin = new Admin();
//        admin.setName("admin");
//        admin.setSurname("admin");
//        admin.setPhone("000000000");
//        admin.setAuth();setPassword("admin");
//        admin.setEmail("admin@email.com");
//        admin.setTest("test");
//
//       // Admin savedAdmin = adminRepository.save(admin);
//
//        List<Pars> integerList = new ArrayList<>();
//        integerList.add(new Par(1));
//        integerList.add(new Par(2));
//
//        integerList.toArray(new Pars[1]);
//
//        //Optional<Admin> adminFromDataBase = adminRepository.findById(savedAdmin.getId());
//
////        adminFromDataBase.ifPresent(value -> log.info(value.toString()));
////        Assertions.assertTrue(adminFromDataBase.isPresent());
//    }
//
//}
