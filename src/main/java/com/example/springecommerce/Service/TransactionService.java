package com.example.springecommerce.Service;

import com.example.springecommerce.Model.Entity.*;
import com.example.springecommerce.Model.Request.TransactionRequest;
import com.example.springecommerce.Model.Request.TransactionResponse;
import com.example.springecommerce.Repository.*;
import com.example.springecommerce.Utils.TransactionMapping;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionDetailRepository transactionDetailRepository;
    @Autowired
    private TransactionMapping transactionMapping;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    @Transactional
    public TransactionDetail save(TransactionRequest transaction) {
        try {
            Transaction newTransaction = modelMapper.map(transaction, Transaction.class);
            TransactionDetail newDetail = modelMapper.map(transaction, TransactionDetail.class);

            Optional<Product> findProduct = productRepository.findById(transaction.getProductId());
            Optional<User> findUser = userRepository.findById(transaction.getUserId());
            Stock stock = findProduct.get().getStock();
            stock.setTotalStock(stock.getTotalStock() - transaction.getQuantity());


            newTransaction.setUser(findUser.get());
            newTransaction.setGrandTotal(findProduct.get().getPrice().getPrice() * transaction.getQuantity());
            findProduct.get().setStock(stock);
            newDetail.setProduct(findProduct.get());
            newDetail.setTransaction(newTransaction);

            Optional<User> find = userRepository.findById(transaction.getUserId());
            newTransaction.setUser(find.get());
            transactionRepository.save(newTransaction);
            return transactionDetailRepository.save(newDetail);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TransactionResponse> findAll() {
        try {

            List<TransactionDetail> find = transactionDetailRepository.findAll();
            return transactionMapping.getTransactionResponses(find);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TransactionResponse> getDailyReport(LocalDate date) {
        try {
            List<TransactionDetail> find = transactionDetailRepository.findByTransaction_TransactionDate(date);
            Double dailyTotal = transactionDetailRepository.getTotalDailyTransaction(date);
            System.out.println(dailyTotal);
            List<TransactionResponse> result = transactionMapping.getTransactionResponses(find);
            for (TransactionResponse transactionResponse : result) {
                transactionResponse.setDailyTotal(String.valueOf(dailyTotal));
            }
            return result;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }



    @Override
    public List<TransactionResponse> getMonthlyReport(int month, int year) {
        try {
            LocalDate startDate = LocalDate.of(year,month,1);
            LocalDate endDate = LocalDate.of(year,month,startDate.lengthOfMonth());
            List<TransactionDetail> find = transactionDetailRepository
                    .findByTransaction_TransactionDateBetween(startDate, endDate);
            List<TransactionResponse> result = transactionMapping.getTransactionResponses(find);
            Double monthlyTotal = transactionDetailRepository.getTotalMonthlyTransaction(startDate, endDate);
            for (TransactionResponse transactionResponse : result) {
                transactionResponse.setMonthlyTotal(String.valueOf(monthlyTotal));
            }
            return result;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Optional<TransactionDetail> find = transactionDetailRepository.findById(id);
            if (find.isEmpty()){
                throw new RuntimeException("Data not found");
            }
            transactionDetailRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
