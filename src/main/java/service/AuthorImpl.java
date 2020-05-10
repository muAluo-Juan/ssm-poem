package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AuthorDao;
import po.Author;
import utils.DynamicDataSourceHolder;

@Service
@Transactional()
public class AuthorImpl implements AuthorService{

	@Autowired
	private AuthorDao authorDao;
	
	@Override
	public Author getAuthorByAuthorId(int id) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return authorDao.getByAuthorId(id);
	}

	@Override
	public List<Author> getAuthorsByAuthorName(String name) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return authorDao.getByAuthorName(name);
	}

	@Override
	public List<Author> getAuthorsByDynastyId(int dynastyId) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return authorDao.getByDynastyId(dynastyId);
	}

	@Override
	public void addAuthor(Author author) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		authorDao.add(author);
	}

	@Override
	public void deleteAuthor(int id) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		authorDao.delete(id);
	}

	@Override
	public void updateAuthor(Author author) {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		authorDao.update(author);
	}

	@Override
	public List<Author> getAllAuthors() {
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return authorDao.getAll();
	}

	@Override
	public Author getAuthorByUid(String uid) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("firstdataSource");
		return authorDao.getByUid(uid);
	}
}
