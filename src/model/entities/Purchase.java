package model.entities;
import java.util.ArrayList;
import lombok.*;
import lombok.Builder;

// Use lombok to create getters, setters and constructors
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    private Member clubMember;
    private ArrayList<Item> item;
    private int price;
    private int shoppingRating;
}
