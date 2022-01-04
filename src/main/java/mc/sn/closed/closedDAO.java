package mc.sn.closed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mc.sn.hospital.evalVO;

@Repository("closedDAO")
public class closedDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean update(ClosedVO vo) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int a = sqlSession.update("mapper.member.closedUpdateById",vo);
		if(a>0) {
			flag=true;
		}
		return flag;
	}

	public List<ClosedVO> selectAll() {
		// TODO Auto-generated method stub
		List<ClosedVO> closedList = new ArrayList<ClosedVO>();		
		closedList = sqlSession.selectList("mapper.member.closedSelectAll");
		return closedList;
	}

	public boolean deleteClosed(String id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int a = sqlSession.delete("mapper.member.closedDeleteById",id);
		if(a>0) {
			flag=true;
		}
		return flag;
	}

	public boolean insertClosed(ClosedVO vo) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int a = sqlSession.insert("mapper.member.closedInsert",vo);
		if(a>0) {
			flag=true;
		}
		return flag;
	}

	public ClosedVO selectOneById(String closedId) {
		// TODO Auto-generated method stub
		ClosedVO vo = sqlSession.selectOne("mapper.member.closedSelectOne",closedId);
		return vo;
	}
	
	public List<ClosedVO> isExist(String name) {
		// TODO Auto-generated method stub
		List<ClosedVO> closedList = new ArrayList<ClosedVO>();
		closedList = sqlSession.selectList("mapper.member.selectClosedByName",name); 
		return closedList;
	}

	public List<evalVO> eval(String hpid) {
		// TODO Auto-generated method stub
		List<evalVO> evalList = new ArrayList<evalVO>();
		evalList = sqlSession.selectList("mapper.member.selectEvalByHpid",hpid); 
		return evalList;
	}

	public boolean insertEval(evalVO vo) {
		// TODO Auto-generated method stub
		boolean flag= false;
		int a = sqlSession.insert("mapper.member.insertEval",vo);
		if(a>0) {
			flag = true;
		}
		return flag;
	}

	public boolean alreadyExist(ClosedVO vo) {
		// TODO Auto-generated method stub
		boolean flag = false;
		ClosedVO vo1 = sqlSession.selectOne("mapper.member.alreadyExist",vo); 
		if(vo1==null) {
			flag= true;
		}
		return flag;
	}

	public List<ClosedVO> searchById(String id) {
		// TODO Auto-generated method stub
		List<ClosedVO> closedList = new ArrayList<ClosedVO>();
		closedList = sqlSession.selectList("mapper.member.searchById",id); 
		return closedList;
	}
}
