package mc.sn.closed;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("closedService")
public class closedService {
	
	@Autowired
	private closedDAO dao;
	
	public boolean change(ClosedVO vo) {
		// TODO Auto-generated method stub
		boolean flag = dao.update(vo);
		return flag;
	}

	public List<ClosedVO> searchAll() {
		// TODO Auto-generated method stub
		List<ClosedVO> closedList = new ArrayList<ClosedVO>();
		closedList = dao.selectAll();
		return closedList;
	}

	public boolean closedDelete(String id) {
		// TODO Auto-generated method stub
		boolean flag = dao.deleteClosed(id);
		return flag;
	}

	public boolean register(ClosedVO vo) {
		// TODO Auto-generated method stub
		boolean flag = dao.insertClosed(vo);
		return flag;
	}

	public ClosedVO searchOneById(String closedId) {
		// TODO Auto-generated method stub
		ClosedVO vo = dao.selectOneById(closedId);
		return vo;
	}

	public boolean alreadyExist(ClosedVO vo) {
		// TODO Auto-generated method stub
		boolean flag = dao.alreadyExist(vo);
		return flag;
	}

	public List<ClosedVO> searchById(String id) {
		// TODO Auto-generated method stub
		List<ClosedVO> closedList = new ArrayList<ClosedVO>();
		closedList = dao.searchById(id);
		return closedList;
	}

}
