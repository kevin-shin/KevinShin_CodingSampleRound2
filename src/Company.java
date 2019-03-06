/**
 * Company class, represents a company object.
 */

public class Company {
    private long id;
    private String company;
    private String city;
    private String timezone;

    public Company(String company, long id, String city, String timezone){
        this.company = company;
        this.id = id;
        this.city = city;
        this.timezone = timezone;
    }

    public Company(String company){
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCompany() {
        return company;
    }

    public String getTimezone() {
        return timezone;
    }

    public String toString(){
        String description = company + " in " + city + " at " + timezone + " timezone.";
        return description;
    }

    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Company) {
            Company company = (Company) obj;
            return (company.company.equals(this.company));
        } else {
            return false;
        }
    }
}
