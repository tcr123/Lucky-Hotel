package com.example.fop_hotel_gui.Table;



public class A001ReviewTable {

    String Review;
    Double Rating;


    public A001ReviewTable(String Review, Double Rating){

        this.Review = Review;
        this.Rating = Rating;

    }



    public String getReview() {
        return Review;
    }

    public void setReview(String Review) {this.Review = Review;}

    public Double getRating (){return Rating;}

    public void setRating(Double RatingA001) {this.Rating = Rating;}


}
