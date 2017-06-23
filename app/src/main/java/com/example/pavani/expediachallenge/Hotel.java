/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.example.pavani.expediachallenge;

import android.os.Parcel;
import android.os.Parcelable;


public class Hotel implements Parcelable {

  public String imgBaseURL = "http://images.travelnow.com";
  public String contentBaseURL = "https://techblog.expedia.com/utility/";
  public String hotelName;
  public String hotelGuestRating;
  public String hotelStarRating;
  public String reviewTotal;
  public String hotelDescription;
  public String longitude;
  public String latitude;
  public String hotelAddress;
  public String priceToShowUsers;
  public String strikethroughPriceToShowUsers;
  public String imgURL;
  public String discountPercent;
  public String roomsLeft;

  public Hotel()
  {
    this.hotelName = "Name";
    this.hotelGuestRating = "0";
    this.hotelStarRating = "0";
    this.reviewTotal = "0";
    this.hotelDescription = "Description";
    this.longitude = "0.0";
    this.latitude = "0.0";
    this.hotelAddress = "Address";
    this.priceToShowUsers = "0";
    this.imgURL = "http://i.imgur.com/RiEIOcB.png";
    this.discountPercent = "0";
    this.roomsLeft = "0";
    this.strikethroughPriceToShowUsers = "0";
  }



  public Hotel(String hotelName, String hotelGuestRating, String hotelStarRating, String reviewTotal, String hotelDescription, String longitude, String latitude, String hotelAddress, String priceToShowUsers, String strikethroughPriceToShowUsers, String imgURL, String discountPercent, String roomsLeft) {
    this.hotelName = hotelName;
    this.hotelGuestRating = hotelGuestRating;
    this.hotelStarRating = hotelStarRating;
    this.reviewTotal = reviewTotal;
    this.hotelDescription = hotelDescription;
    this.longitude = longitude;
    this.latitude = latitude;
    this.hotelAddress = hotelAddress;
    this.priceToShowUsers = priceToShowUsers;
    this.imgURL = imgBaseURL + imgURL;
    this.discountPercent = discountPercent;
    this.roomsLeft = roomsLeft;
    this.strikethroughPriceToShowUsers = strikethroughPriceToShowUsers;
  }

  public String getHotelName() {
    return hotelName;
  }

  public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }

  public String getHotelGuestRating() {
    return hotelGuestRating;
  }

  public void setHotelGuestRating(String hotelGuestRating) {
    this.hotelGuestRating = hotelGuestRating;
  }

  public String getHotelStarRating() {
    return hotelStarRating;
  }

  public void setHotelStarRating(String hotelStarRating) {
    this.hotelStarRating = hotelStarRating;
  }

  public String getReviewTotal() {
    return reviewTotal;
  }

  public void setReviewTotal(String reviewTotal) {
    this.reviewTotal = reviewTotal;
  }

  public String getHotelDescription() {
    return hotelDescription;
  }

  public void setHotelDescription(String hotelDescription) {
    this.hotelDescription = hotelDescription;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getHotelAddress() {
    return hotelAddress;
  }

  public void setHotelAddress(String hotelAddress) {
    this.hotelAddress = hotelAddress;
  }

  public String getPriceToShowUsers() {
    return priceToShowUsers;
  }

  public void setPriceToShowUsers(String priceToShowUsers) {
    this.priceToShowUsers = priceToShowUsers;
  }

  public String getStrikethroughPriceToShowUsers() {
    return strikethroughPriceToShowUsers;
  }

  public void setStrikethroughPriceToShowUsers(String strikethroughPriceToShowUsers) {
    this.strikethroughPriceToShowUsers = strikethroughPriceToShowUsers;
  }

  public String getImgURL() {
    return imgURL;
  }

  public void setImgURL(String imgURL) {
    this.imgURL = imgURL;
  }

  public String getDiscountPercent() {
    return discountPercent;
  }

  public void setDiscountPercent(String discountPercent) {
    this.discountPercent = discountPercent;
  }

  public String getRoomsLeft() {
    return roomsLeft;
  }

  public void setRoomsLeft(String roomsLeft) {
    this.roomsLeft = roomsLeft;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.imgBaseURL);
    dest.writeString(this.contentBaseURL);
    dest.writeString(this.hotelName);
    dest.writeString(this.hotelGuestRating);
    dest.writeString(this.hotelStarRating);
    dest.writeString(this.reviewTotal);
    dest.writeString(this.hotelDescription);
    dest.writeString(this.longitude);
    dest.writeString(this.latitude);
    dest.writeString(this.hotelAddress);
    dest.writeString(this.priceToShowUsers);
    dest.writeString(this.strikethroughPriceToShowUsers);
    dest.writeString(this.imgURL);
    dest.writeString(this.discountPercent);
    dest.writeString(this.roomsLeft);

  }

  protected Hotel(Parcel in) {
    this.imgBaseURL = in.readString();
    this.contentBaseURL = in.readString();
    this.hotelName = in.readString();
    this.hotelGuestRating = in.readString();
    this.hotelStarRating = in.readString();
    this.reviewTotal = in.readString();
    this.hotelDescription = in.readString();
    this.longitude = in.readString();
    this.latitude = in.readString();
    this.hotelAddress = in.readString();
    this.priceToShowUsers = in.readString();
    this.strikethroughPriceToShowUsers = in.readString();
    this.imgURL = in.readString();
    this.discountPercent = in.readString();
    this.roomsLeft = in.readString();
  }

  public static final Parcelable.Creator<Hotel> CREATOR = new Parcelable.Creator<Hotel>() {
    @Override
    public Hotel createFromParcel(Parcel source) {
      return new Hotel(source);
    }

    @Override
    public Hotel[] newArray(int size) {
      return new Hotel[size];
    }
  };
}
