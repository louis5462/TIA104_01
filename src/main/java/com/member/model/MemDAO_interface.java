package com.member.model;
import java.util.*;

public interface MemDAO_interface  {
	public void insert (MemVO memVO);
	public void update (MemVO memVO);
	public MemVO findByPK (Integer memberID);
	public void delete (Integer memberID);
	public List<MemVO> getAll();
}
