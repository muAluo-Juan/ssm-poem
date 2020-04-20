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
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		return authorDao.getByAuthorId(id);
	}

	@Override
	public List<Author> getAuthorsByAuthorName(String name) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		return authorDao.getByAuthorName(name);
	}

	@Override
	public List<Author> getAuthorsByDynastyId(int dynastyId) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		return authorDao.getByDynastyId(dynastyId);
	}

	@Override
	public void addAuthor(Author author) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		authorDao.add(author);
	}

	@Override
	public void deleteAuthor(int id) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		authorDao.delete(id);
	}

	@Override
	public void updateAuthor(Author author) {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		authorDao.update(author);
	}

	@Override
	public List<Author> getAllAuthors() {
		DynamicDataSourceHolder.setDataSource("seconddataSource");
		return authorDao.getAll();
	}
}
