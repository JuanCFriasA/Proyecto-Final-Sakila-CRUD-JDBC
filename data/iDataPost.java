package data;
import java.util.List;

public interface iDataPost<T>{
    boolean post(T e);
    T get(int id);
    List<T> get();
    List<T> get(String filter);
    boolean put(T e);
    boolean delete(int id);
}
