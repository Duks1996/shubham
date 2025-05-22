package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Booking;
import com.airbnb.entity.Property;
import com.airbnb.entity.Room;
import com.airbnb.repository.BookingRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    RoomRepository roomRepository;
    PropertyRepository propertyRepository;
    BookingRepository bookingRepository;

    public BookingService(PropertyRepository propertyRepository,RoomRepository roomRepository,BookingRepository bookingRepository) {
        this.propertyRepository = propertyRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    public String createBooking(long propertyId, Booking booking, AppUser appUser) {
        List<LocalDate> datesBetween = getDatesBetween(booking.getCheckInDate(), booking.getCheckOutDate());

        Room room = roomRepository.findByTypeAndPropertyId(booking.getTypeOfRoom(), propertyId);
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property not found"));

        if(room.getCount()==0){
            return "Rooms not available";
        }else{
            float nightlyPrice = room.getPrice();
            float totalPrice = nightlyPrice * booking.getTotalNights();
//
            booking.setTotalPrice(totalPrice);
            booking.setProperty(property);
            booking.setAppUser(appUser);

            Booking savedBooking = bookingRepository.save(booking);
            if(savedBooking!=null){
                room.setCount(room.getCount()-1);
                roomRepository.save(room);
            }
            return "Room Booked";
        }
    }

    public List<LocalDate> getDatesBetween(LocalDate checkInDate,LocalDate checkOutDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = checkInDate;

        while (!currentDate.isAfter(checkOutDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return dates;
    }

}
