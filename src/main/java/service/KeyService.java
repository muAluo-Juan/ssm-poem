package service;



import java.util.List;
import po.Key;

public interface KeyService {
  public void addKey(Key key);
  public void deleteKey(long kId);
  public Key getKeyById(long kId);
  public List<Key> getAllKeys();
}
