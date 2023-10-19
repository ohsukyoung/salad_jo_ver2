import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class ad_login {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String adId, adPw;                         // 관리자 ID와 Password 변수 선언
    public static boolean kioskFlag;
    public void adLogin() throws IOException, ClassNotFoundException   // 나중에 main으로 떨어져서 문제생기면 trycatch로 변경하기
    {
        System.out.println();
        System.out.println("\t관리자 로그인을 시작합니다.");

        while(true)
        {

            System.out.println("\n\t===============================");
            System.out.print("\t▶ 관리자 ID 입력 : ");
            adId = br.readLine();
            System.out.print("\t▶ 관리자 비밀번호 입력 : ");
            adPw = br.readLine();
            System.out.println("\t===============================");
			System.out.println();

            if (adId.equals("admin") && adPw.equals("1234"))
            {
                System.out.println("\t「 관리자 로그인 되었습니다. 」");
                System.out.println("\t「 관리자 모드를 실행합니다. 」");
                break;
            }
            else
            {
                System.out.println("\t[!] 잘못된 ID 또는 Password 입니다. 다시 입력해주세요.");
                System.out.println();
            }
        }
        while(true)
        {
            KioskMg km = new KioskMg();
            km.adMenuDisp();
            km.adMenuSelect();
            km.adMenuRun();
        }



    }
}
