import java.io.IOException;

public interface Impl_admin
{
    void ad_print()throws IOException, ClassNotFoundException; // 출력 메소드

    void ad_add()throws IOException, ClassNotFoundException; // 생성 메소드

    void ad_modify()throws IOException, ClassNotFoundException; // 수정 메소드

    void ad_delete()throws IOException, ClassNotFoundException; // 삭제 메소드
}