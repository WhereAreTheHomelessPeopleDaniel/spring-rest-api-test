package com.example.demo;

import com.example.demo.models.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<HotelBooking, Long>{
    List<HotelBooking> findByPricePerNightLessThan(double price);
}