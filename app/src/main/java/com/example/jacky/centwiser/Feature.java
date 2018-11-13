package com.example.jacky.centwiser;

public class Feature {
    private String type;

    private String name;

    private String description;

    private String category;

    private String hours;

    private String location;

    private String pc;

    private String phone;

    private String email;

    private String website;

    private String x;

    private String y;

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public String getCategory(){
        return this.category;
    }
    public void setHours(String hours){
        this.hours = hours;
    }
    public String getHours(){
        return this.hours;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }
    public void setPC(String pc){
        this.pc = pc;
    }
    public String getPC(){
        return this.pc;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setWebsite(String website){
        this.website = website;
    }
    public String getWebsite(){
        return this.website;
    }
    public void setX(String x){
        this.x = x;
    }
    public String getX(){
        return this.x;
    }
    public void setY(String y){
        this.y = y;
    }
    public String getY(){
        return this.y;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(type);
        sb.append(System.getProperty("line.separator"));
        sb.append(name);
        sb.append(System.getProperty("line.separator"));
        sb.append(description);
        sb.append(System.getProperty("line.separator"));
        sb.append(category);
        sb.append(System.getProperty("line.separator"));
        sb.append(hours);
        sb.append(System.getProperty("line.separator"));
        sb.append(location);
        sb.append(System.getProperty("line.separator"));
        sb.append(pc);
        sb.append(System.getProperty("line.separator"));
        sb.append(phone);
        sb.append(System.getProperty("line.separator"));
        sb.append(email);
        sb.append(System.getProperty("line.separator"));
        sb.append(x);
        sb.append(System.getProperty("line.separator"));
        sb.append(y);
        sb.append(System.getProperty("line.separator"));

        return (new String(sb));
    }
}
