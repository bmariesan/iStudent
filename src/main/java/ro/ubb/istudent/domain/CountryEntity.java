package ro.ubb.istudent.domain;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/*@Data
@Builder
@EqualsAndHashCode(callSuper = true) commented by valer*/
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "countries")
public class CountryEntity extends BaseEntity {

    @Indexed(unique=true)
    private String countryName;

    //+Valer ce e sub asta


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public static CountryEntityBuilder builder(){
        return  new CountryEntityBuilder();
    }

    public static class CountryEntityBuilder{

        @Indexed(unique=true)
        private String countryName;

        public CountryEntityBuilder countryName(String countryName){
            this.countryName = countryName;
            return this;
        }

        public CountryEntity build(){
            return new CountryEntity(this);
        }
    }

    private CountryEntity(CountryEntityBuilder countryEntityBuilder){
        this.countryName = countryEntityBuilder.countryName;
    }
}
