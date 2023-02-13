package controler;

import java.util.ArrayList;

public interface IDAO<T> {
	public void create(T object);
	public ArrayList<T> read();
	public void update(T object);
	public void delete(T object);
}
