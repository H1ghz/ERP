package com.erp.dto;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.vo.*;

@Repository
public class noticeDTO 
{
	@Autowired
	private SqlSession session;
	
	private final static String namespace = "com.erp.notice";
	
	//C:���������� ����Ѵ�.
	//�Ű����� : vo - ����� �������� ����
	//���ϰ� : true - ��� ����, false - ��� ����	
	public boolean noticeInsert(noticeVO vo,String[] notice_team)
	{
		session.insert(namespace + ".notice_insert",vo);
	
		for(String team : notice_team)
		{
			notice_teamVO teamVO = new notice_teamVO();
			teamVO.setNotice_no(vo.getNotice_no());
			teamVO.setNotice_team(team);
			//System.out.println("teamVO.getNotice_no():" + teamVO.getNotice_no());
			//System.out.println("teamVO.getNotice_team():" + teamVO.getNotice_team());
			session.insert(namespace + ".insert_team",teamVO);
		}
		
		return true;
	}
	
	//R:�������� ��ü ������ ��´�.
	//�Ű����� : searchVO -> notice_team -> ��� ���� 
	//���ϰ� : ��ü �������� ����
	public int GetTotal(searchVO vo)
	{
		int total = session.selectOne(namespace + ".total",vo);
		
		return total;
	}
	
	//R:�������� ����� ��ȸ�Ѵ�.
	//�Ű����� : searchVO
	//���ϰ� : �������� ��� List
	public List<noticeVO> noticeList(searchVO vo)
	{
		List<noticeVO> list = session.selectList(namespace + ".notice_list",vo);
		return list;
	}
	
	// �μ� ����Ʈ (�� �޴���)
	public List<notice_teamVO> noticeTeamList() {
	    return session.selectList(namespace + ".notice_team_list");
	}
	
	//R:�������� ������ ��ȸ�Ѵ�.
	//�Ű����� : notice_no - ��ȸ�� �������� ��ȣ 
	//���ϰ� : noticeVO - �������� ����
	public noticeVO getNoticeInfo(int notice_no)
	{
		noticeVO vo = session.selectOne(namespace + ".notice_view",notice_no);
		
		return vo;
	}
	
	//R:���������� �������� ������ ��ȸ�Ѵ�.
	//�Ű����� : notice_no - ��ȸ�� �������� ��ȣ 
	//���ϰ� : notice_teamVO - �������� �μ� ��� ����
	public List<notice_teamVO> getNoticeTeam(int notice_no)
	{
		 List<notice_teamVO> list = session.selectList(namespace + ".notice_team",notice_no);
		
		return list;
	}	
	
	//R:����ȭ�鿡 �������� ����� ��ȸ�Ѵ�.
	//�Ű����� : 
	//���ϰ� : �������� ��� List
	public List<noticeVO> mainList()
	{
		List<noticeVO> list = session.selectList(namespace + ".main_List");
		return list;
	}
	
	//D:�������� ������ �����Ѵ�.
	//�Ű����� : notice_no - ���� �� �������� ��ȣ
	//���ϰ� : true - ���� ����, false - ���� ����	
	public boolean noticeDelete(int notice_no) 
	{
		noticeVO vo = new noticeVO();
		vo.setNotice_no(notice_no);;
		
		session.delete(namespace + ".notice_delete",vo);
		return true;
	}
	
	
}
