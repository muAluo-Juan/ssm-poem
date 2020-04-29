package po;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DraftTest {
	private Draft draft;
	@Before
	public void setUp() throws Exception{
		draft = new Draft();
		draft.setDraftId(12);
		draft.setText("这个是测试的");
	}

	@Test
	public void testGetDraftId() {
		assertEquals("获得草稿id这个有问题",12,draft.getDraftId());
	}

	@Test
	public void testGetText() {
		assertEquals("这个草稿的text有问题","这个是测试的",draft.getText());
	}

}
