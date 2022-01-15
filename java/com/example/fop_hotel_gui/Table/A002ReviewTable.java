package com.example.fop_hotel_gui.Table;



public class A002ReviewTable {

    String Review;
    Double Rating;


    public A002ReviewTable(String Review, Double Rating){

        this.Review = Review;
        this.Rating = Rating;

    }



    public String getReview() {return Review;}

    public void setReview(String Review) {this.Review = Review;}

    public Double getRating (){return Rating;}

    public void setRating(Double Rating) {this.Rating = Rating;}


}
