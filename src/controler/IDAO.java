package controler;

import java.util.ArrayList;

public interface IDAO<T> {
	public boolean create(T object);
	public ArrayList<T> read();
	public void update(T object);
	public boolean delete(T object);
}
