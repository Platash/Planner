package planner.entity;

/**
 * Created by Anna Platash on 4/15/16.
 */

import javax.persistence.Entity;

import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {

    public String getName() {
        return "Dusia";
    }


}
