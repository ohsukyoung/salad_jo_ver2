import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class MemberMg implements Impl_admin
{
    public final int E_PRINT = 1;                    //-- 요소(회원) 추가
    public final int E_ADD = 2;
    public final int E_MOD = 3;
    public final int E_DEL = 4;
    public final int E_EXIT = 5;

    public static HashMap<String, Member> hm; //-- 자료구조 선언
    private static BufferedReader br;		         //-- 사용자가 입력시 활용
    private static Integer sel;				         //-- 선택 값
    private static String con;				         //-- 계속 진행 여부

    static
    {
        //hashmap 자료구조 생성
        hm = new HashMap<String, Member>();

        //BufferedReader 객체 생성
        br = new BufferedReader(new InputStreamReader(System.in));

        // 사용자 입력값 초기화
        sel = 1;
        con = "Y";
    }

    // 메뉴 출력 메소드
    public void menuDisp()
    {
        System.out.println("\n\t[ 회원관리 메뉴 선택 ]===========");
        System.out.println("\t1. 회원 목록 출력");
        System.out.println("\t2. 회원 등록");
        System.out.println("\t3. 회원 정보 수정");
        System.out.println("\t4. 회원 삭제");
        System.out.println("\t5. 관리자 메뉴 화면으로 이동");
        System.out.println("\t=================================");
        System.out.print("\t▶ 메뉴 선택(1~5) : ");
    }

    // 메뉴 선택 메소드
    public void menuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }

    // 메뉴 실행에 따른 기능 호출 메소드
    public void menuRun() throws IOException
    {
        switch (sel)
        {
            case E_PRINT : ad_print(); break;
            case E_ADD : ad_add(); break;
            case E_MOD : ad_modify(); break;
            case E_DEL : ad_delete(); break;
            case E_EXIT : exit(); break;
            default : System.out.print("\t[!] 메뉴 선택 오류");
        }
    }

    // 요소(회원) 출력 메소드
    @Override
    public void ad_print() {
        System.out.println();
        System.out.println("\t[ 회원목록 ]------------------------------");
        System.out.println("\tID(전화번호)    password    보유한 포인트");
        Iterator it = hm.keySet().iterator();            // key 값만으로 이루어진 Iterator 생성
        while (it.hasNext())                             // Iterator 안에 다음 요소가 있는지? 있으면 true 반환
        {
            String key = (String)it.next();              // next()로 해당 요소에 접근
            System.out.println("\t" + hm.get(key));      // key 값을 넣으면 value 값(Member 객체)을 반환

        }
        System.out.println("\t-----------------------------------------");
        System.out.println();
    }


    // 요소(회원) 추가 메소드
    @Override
    public void ad_add(){

            try
            {
                System.out.println();
                System.out.println("\t「 회원 생성을 시작합니다. 」");
                System.out.println("\t「 생성할 회원의 정보를 입력해주세요. 」");

                while (true)
                {
                    boolean idCheck = false;                                     // 선언 및 초기화
                    System.out.print("\t▶ ID 입력(전화번호) : ");
                    String id = br.readLine();                                   // 관리자가 ID를 입력
                    char[] arr1 = id.toCharArray();                              // 관리자가 입력한 id를 char 배열 arr1에 쪼개서 담기
                    for (int i=3; i<id.length(); i++)                            // 010 뒷부분은 인덱스 3~10
                    {
                        if ('0'<=arr1[i] && arr1[i]<='9')                        // 010 뒷부분을 0에서 9까지의 숫자형태로 입력했는지 확인
                            idCheck = true;
                    }

                    if (hm.containsKey(id))
                        System.out.println("\n\t[!] 이미 존재하는 ID입니다.");
                    else if (id.length()==11 && idCheck && id.substring(0,3).equals("010"))   // 총 11자리가 이고, 010으로 시작하고, 010 뒷부분을 숫자형태로 입력한 경우
                    {
                        String memPw;
                        while(true) {
                            boolean pwCheck = false;                              // 선언 및 초기화
                            System.out.print("\t▶ Password 입력(숫자 4자리) : ");
                            memPw = br.readLine();                                // 관리자가 Password를 입력
                            char[] arr2 = memPw.toCharArray();                    // 관리자가 입력한 Password를 char 배열 arr2에 쪼개서 담기
                            for (int i = 0; i <memPw.length(); i++)               // 인덱스 0~3
                                if ('0' <= arr2[i] && arr2[i] <= '9')             // 0에서 9까지의 숫자형태로 입력했는지 확인
                                    pwCheck = true;
                            if (memPw.length() == 4 && pwCheck)                  // 총 4자리이거나, 숫자형태로 입력한 경우
                                break;
                            else
                                System.out.println("\n\t[!] Password는 숫자 4자리만 입력가능합니다. 다시 입력해주세요.");
                        }
                        hm.put(id,new Member(id,memPw,1000));
                        System.out.println("\n\t「 회원등록이 완료되었습니다. 」");
                        break;
                    }
                    else
                        System.out.println("\n\t[!] ID는 전화번호만 입력가능합니다. 다시 입력해주세요.");
                }
            }
            catch(IOException e)
            {
                System.out.println(e.toString());
            }


        //hm.put("123",new Member("1234",1000,"123"));
        //hm.put("567",new Member("5678", 1000, "567"));
    }


    // 요소(회원) 수정 메소드
    @Override
    public void ad_modify()
    {
        try
        {
            System.out.println();
            System.out.println("\t「 정보를 수정할 회원의 ID를 입력하세요. 」");
            System.out.print("\t▶ ID : ");
            String id = br.readLine();
            if (!hm.containsKey(id))                           // 입력한 ID를 가진 회원이 존재하는지 확인
            {
                System.out.println("\t[!] 입력하신 회원 ID가 존재하지 않습니다.");
            }
            else
            {
                // 회원 정보 수정
                System.out.println("\t「 수정할 password를 입력하세요. 」");
                System.out.print("\t▶ password : ");
                String memPw = br.readLine();
                System.out.println("\t「 수정할 point를 입력하세요. 」");
                System.out.print("\t▶ point : ");
                int memPoint = Integer.parseInt(br.readLine());
                hm.put(id,new Member(id,memPw,memPoint));     // 입력한 정보를 넣어서 value 값 바꿔주기
                System.out.println("\t「 회원 정보 수정이 완료되었습니다. 」");
            }
        }
        catch(IOException e)
        {
            System.out.println(e.toString());
        }
    }


    // 요소(회원) 삭제 메소드
    @Override
    public void ad_delete()
    {

        try
        {
            System.out.println("\n\t「 삭제할 회원의 ID를 입력하세요. 」");
            System.out.print("\t▶ ID : ");
            String id = br.readLine();
            if (!hm.containsKey(id))                           // 입력한 ID를 가진 회원이 존재하는지 확인
            {
                System.out.println("\t[!] 입력하신 회원 ID가 존재하지 않습니다.");
            }
            else
            {
                hm.remove(id);
                System.out.println("\t「 회원 삭제가 정상적으로 완료되었습니다. 」");
            }

        }
        catch(IOException e)
        {
            System.out.println(e.toString());
        }
    }

    // 종료가 아니라 다시 관리자 메뉴로 돌아가게 수정해야함
    public void exit()
    {
        System.out.println("\n\t「 관리자 메뉴로 돌아갑니다. 」");
        KioskMg.memflag = false;
    }
}

