import java.io.Serializable;
public class Member implements Serializable
{
    private static final long serialVersionUID = -560608037096839696L;
    private String id;
    private String memPw;
    private int memPoint;
    Member(String id,String memPw,int memPoint){
        this.memPw = memPw;
        this.memPoint = memPoint;
        this.id = id;
    }

    public Member() {

    }

    public void setPhoneNum(String phoneNum){
        this.id = phoneNum;
    }

    public String getPhoneNum(){
        return id;
    }

    public void setMemPw(String memPw){
        this.memPw = memPw;
    }

    public String getMemPw(){
        return memPw;
    }

    public void setMemPoint(int memPoint){
        this.memPoint = memPoint;
    }

    public int getMemPoint(){
        return memPoint;
    }

    @Override
    public String toString() {
        return id +"        "+ memPw +"           "+ memPoint;
    }
}