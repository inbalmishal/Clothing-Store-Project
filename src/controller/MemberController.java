package controller;
import model.*;
import model.entities.Member;

import java.util.ArrayList;

// Connect between the view to the model
public class MemberController {

    protected MemberModel model;

    public MemberController() {
        this.model = new MemberModel();
    }

    public boolean addClubMember(Member m)
    {
       return model.addClubMember(m);
    }

    public boolean deleteClubMember(int id)
    {
       return model.deleteClubMember(id);
    }

    public boolean isExistsClubMember(int id)
    {
        return model.isExistsClubMember(id);
    }

    public Member searchMember(int id){return model.searchMember(id);}

    public ArrayList<Member> birthdayPointAuto()
    {
        return model.birthdayPointAuto();
    }

    public void updateMembersPoints(int price, Member m)
    {
        model.updateMembersPoints(price, m);
    }
}
