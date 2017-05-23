package Service;


import java.util.Collection;

public interface PrinterService {

    <T> void print(Collection<T> values);

    <T> void print(T value);
}