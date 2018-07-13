package com.cevs.rentall.models;

public class VehicleRenter {
    private int id;
    private String manufacturer;
    private String model;
    private int year;
    private int fuelTank;
    private int mileage;
    private String engine;
    private int fuelConsumption;
    private int spareTires;
    private int weight;
    private int payloadCapacity;
    private String additionalEquipment;
    private String registrationPlate;
    private String vehicleType;
    private String vehicleSubtype;
    private boolean available;
    private int pricePerDay;
    private String image;

    private int companyId;
    private String email;
    private Location location;
    private String  companyImage;
    private String companyName;
    private String companyPhoneNumber;
    private String bankAccount;

    public VehicleRenter() {
    }

    public VehicleRenter(int id, String manufacturer, String model, int year, int fuelTank, int mileage,
                         String engine, int fuelConsumption, int spareTires, int weight, int payloadCapacity,
                         String additionalEquipment, String registrationPlate, String vehicleType,
                         String vehicleSubtype, boolean available, int pricePerDay, String image,
                         String email, Location location, String companyImage, String companyName, String companyPhoneNumber,
                         String bankAccount, int companyId) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.fuelTank = fuelTank;
        this.mileage = mileage;
        this.engine = engine;
        this.fuelConsumption = fuelConsumption;
        this.spareTires = spareTires;
        this.weight = weight;
        this.payloadCapacity = payloadCapacity;
        this.additionalEquipment = additionalEquipment;
        this.registrationPlate = registrationPlate;
        this.vehicleType = vehicleType;
        this.vehicleSubtype = vehicleSubtype;
        this.available = available;
        this.pricePerDay = pricePerDay;
        this.image = image;
        this.email = email;
        this.location = location;
        this.companyImage = companyImage;
        this.companyName = companyName;
        this.companyPhoneNumber = companyPhoneNumber;
        this.bankAccount = bankAccount;
        this.companyId = companyId;
    }

    public VehicleRenter(VehicleRenter vehicleRenter) {
        this.id = vehicleRenter.getId();
        this.manufacturer = vehicleRenter.getManufacturer();
        this.model = vehicleRenter.getModel();
        this.year = vehicleRenter.getYear();
        this.fuelTank = vehicleRenter.getFuelTank();
        this.mileage = vehicleRenter.getMileage();
        this.engine = vehicleRenter.getEngine();
        this.fuelConsumption = vehicleRenter.getFuelConsumption();
        this. spareTires = vehicleRenter.getSpareTires();
        this.weight = vehicleRenter.getWeight();
        this.payloadCapacity = vehicleRenter.getPayloadCapacity();
        this.additionalEquipment = vehicleRenter.getAdditionalEquipment();
        this. registrationPlate = vehicleRenter.getRegistrationPlate();
        this.vehicleType = vehicleRenter.getVehicleType();
        this.vehicleSubtype = vehicleRenter.getVehicleSubtype();
        this.available = vehicleRenter.isAvailable();
        this.pricePerDay = vehicleRenter.getPricePerDay();
        this.image = vehicleRenter.getImage();
        this.email = vehicleRenter.getEmail();
        this.location = vehicleRenter.getLocation();
        this.companyImage = vehicleRenter.getCompanyImage();
        this.companyName = vehicleRenter.getCompanyName();
        this.companyPhoneNumber = vehicleRenter.getCompanyPhoneNumber();
        this.bankAccount = vehicleRenter.getBankAccount();
        this.companyId = vehicleRenter.getCompanyId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFuelTank() {
        return fuelTank;
    }

    public void setFuelTank(int fuelTank) {
        this.fuelTank = fuelTank;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public int getSpareTires() {
        return spareTires;
    }

    public void setSpareTires(int spareTires) {
        this.spareTires = spareTires;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPayloadCapacity() {
        return payloadCapacity;
    }

    public void setPayloadCapacity(int payloadCapacity) {
        this.payloadCapacity = payloadCapacity;
    }

    public String getAdditionalEquipment() {
        return additionalEquipment;
    }

    public void setAdditionalEquipment(String additionalEquipment) {
        this.additionalEquipment = additionalEquipment;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleSubtype() {
        return vehicleSubtype;
    }

    public void setVehicleSubtype(String vehicleSubtype) {
        this.vehicleSubtype = vehicleSubtype;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(String companyImage) {
        this.companyImage = companyImage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
