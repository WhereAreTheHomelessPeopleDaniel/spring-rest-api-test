package com.example.demo;

import com.example.demo.models.HotelBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Access-Control-Allow-Origin")
@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<HotelBooking> getAll(){
        return bookingRepository.findAll();
    }

    @RequestMapping(value = "/affordable/{price}", method = RequestMethod.GET)

    public List<HotelBooking> getAffordable(@PathVariable double price){
        return bookingRepository.findByPricePerNightLessThan(price);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)

    public List<HotelBooking> create(@RequestBody HotelBooking hotelBooking){
        bookingRepository.save(hotelBooking);

        return bookingRepository.findAll();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)

    public List<HotelBooking> remove(@PathVariable long id){
        bookingRepository.deleteById(id);

        return bookingRepository.findAll();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)

    public List<HotelBooking> update(@RequestBody HotelBooking hotelBooking ,@PathVariable long id){
        HotelBooking hb = bookingRepository.findById(id).get();
        hb.setHotelName(hotelBooking.getHotelName());
        hb.setNbOfNights(hotelBooking.getNbOfNights());
        hb.setPricePerNight(hotelBooking.getPricePerNight());
        bookingRepository.save(hb);

        return bookingRepository.findAll();
    }

}