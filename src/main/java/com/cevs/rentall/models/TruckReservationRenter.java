package com.cevs.rentall.models;

import java.sql.Date;

public class TruckReservationRenter extends VehicleReservationRenter {
    private VehicleReservationRenter vrr;
    private float truckHeight;
    private boolean trailer;
    private float trailerLength;
    private float trailerWidth;
    private float trailerHeight;
    private int freightSpace;

    public TruckReservationRenter() {
    }

    public TruckReservationRenter(VehicleReservationRenter vrr){
        this.vrr = vrr;
    }


    public float getTruckHeight() {
        return truckHeight;
    }

    public void setTruckHeight(float truckHeight) {
        this.truckHeight = truckHeight;
    }

    public boolean isTrailer() {
        return trailer;
    }

    public void setTrailer(boolean trailer) {
        this.trailer = trailer;
    }

    public float getTrailerLength() {
        return trailerLength;
    }

    public void setTrailerLength(float trailerLength) {
        this.trailerLength = trailerLength;
    }

    public float getTrailerWidth() {
        return trailerWidth;
    }

    public void setTrailerWidth(float trailerWidth) {
        this.trailerWidth = trailerWidth;
    }

    public float getTrailerHeight() {
        return trailerHeight;
    }

    public void setTrailerHeight(float trailerHeight) {
        this.trailerHeight = trailerHeight;
    }

    public int getFreightSpace() {
        return freightSpace;
    }

    public void setFreightSpace(int freightSpace) {
        this.freightSpace = freightSpace;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getManufacturer() {
        return super.getManufacturer();
    }

    @Override
    public void setManufacturer(String manufacturer) {
        super.setManufacturer(manufacturer);
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public void setModel(String model) {
        super.setModel(model);
    }

    @Override
    public int getYear() {
        return super.getYear();
    }

    @Override
    public void setYear(int year) {
        super.setYear(year);
    }

    @Override
    public int getFuelTank() {
        return super.getFuelTank();
    }

    @Override
    public void setFuelTank(int fuelTank) {
        super.setFuelTank(fuelTank);
    }

    @Override
    public int getMileage() {
        return super.getMileage();
    }

    @Override
    public void setMileage(int mileage) {
        super.setMileage(mileage);
    }

    @Override
    public String getEngine() {
        return super.getEngine();
    }

    @Override
    public void setEngine(String engine) {
        super.setEngine(engine);
    }

    @Override
    public int getFuelConsumption() {
        return super.getFuelConsumption();
    }

    @Override
    public void setFuelConsumption(int fuelConsumption) {
        super.setFuelConsumption(fuelConsumption);
    }

    @Override
    public int getSpareTires() {
        return super.getSpareTires();
    }

    @Override
    public void setSpareTires(int spareTires) {
        super.setSpareTires(spareTires);
    }

    @Override
    public int getWeight() {
        return super.getWeight();
    }

    @Override
    public void setWeight(int weight) {
        super.setWeight(weight);
    }

    @Override
    public int getPayloadCapacity() {
        return super.getPayloadCapacity();
    }

    @Override
    public void setPayloadCapacity(int payloadCapacity) {
        super.setPayloadCapacity(payloadCapacity);
    }

    @Override
    public String getAdditionalEquipment() {
        return super.getAdditionalEquipment();
    }

    @Override
    public void setAdditionalEquipment(String additionalEquipment) {
        super.setAdditionalEquipment(additionalEquipment);
    }

    @Override
    public String getRegistrationPlate() {
        return super.getRegistrationPlate();
    }

    @Override
    public void setRegistrationPlate(String registrationPlate) {
        super.setRegistrationPlate(registrationPlate);
    }

    @Override
    public String getVehicleType() {
        return super.getVehicleType();
    }

    @Override
    public void setVehicleType(String vehicleType) {
        super.setVehicleType(vehicleType);
    }

    @Override
    public String getVehicleSubtype() {
        return super.getVehicleSubtype();
    }

    @Override
    public void setVehicleSubtype(String vehicleSubtype) {
        super.setVehicleSubtype(vehicleSubtype);
    }

    @Override
    public boolean isAvailable() {
        return super.isAvailable();
    }

    @Override
    public void setAvailable(boolean available) {
        super.setAvailable(available);
    }

    @Override
    public int getPricePerDay() {
        return super.getPricePerDay();
    }

    @Override
    public void setPricePerDay(int pricePerDay) {
        super.setPricePerDay(pricePerDay);
    }

    @Override
    public String getImage() {
        return super.getImage();
    }

    @Override
    public void setImage(String image) {
        super.setImage(image);
    }

    @Override
    public int getReservationId() {
        return super.getReservationId();
    }

    @Override
    public void setReservationId(int reservationId) {
        super.setReservationId(reservationId);
    }

    @Override
    public Date getReserveFrom() {
        return super.getReserveFrom();
    }

    @Override
    public void setReserveFrom(Date reserveFrom) {
        super.setReserveFrom(reserveFrom);
    }

    @Override
    public Date getReserveTo() {
        return super.getReserveTo();
    }

    @Override
    public void setReserveTo(Date reserveTo) {
        super.setReserveTo(reserveTo);
    }

    @Override
    public String getStatus() {
        return super.getStatus();
    }

    @Override
    public void setStatus(String status) {
        super.setStatus(status);
    }

    @Override
    public int getCompanyId() {
        return super.getCompanyId();
    }

    @Override
    public void setCompanyId(int companyId) {
        super.setCompanyId(companyId);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public Location getLocation() {
        return super.getLocation();
    }

    @Override
    public void setLocation(Location location) {
        super.setLocation(location);
    }

    @Override
    public String getCompanyImage() {
        return super.getCompanyImage();
    }

    @Override
    public void setCompanyImage(String companyImage) {
        super.setCompanyImage(companyImage);
    }

    @Override
    public String getCompanyName() {
        return super.getCompanyName();
    }

    @Override
    public void setCompanyName(String companyName) {
        super.setCompanyName(companyName);
    }

    @Override
    public String getCompanyPhoneNumber() {
        return super.getCompanyPhoneNumber();
    }

    @Override
    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        super.setCompanyPhoneNumber(companyPhoneNumber);
    }

    @Override
    public String getBankAccount() {
        return super.getBankAccount();
    }

    @Override
    public void setBankAccount(String bankAccount) {
        super.setBankAccount(bankAccount);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
