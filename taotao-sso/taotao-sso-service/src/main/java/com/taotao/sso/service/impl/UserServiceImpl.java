package com.taotao.sso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private TbUserMapper userMapper;
	@Override
	public TaotaoResult checkData(String data, int type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		//1.判断用户名是否可用
		if (type == 1) {
			criteria.andUsernameEqualTo(data);
		//2判断手机号是否可以使用
		} else if (type == 2) {
			criteria.andPhoneEqualTo(data);
		//3判断邮箱是否可以使用
		} else if (type == 3) {
			criteria.andEmailEqualTo(data);
		} else {
			return TaotaoResult.build(400, "参数中包含非法数据");
		}
		//执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		if (list !=null && list.size() > 0) {
			//查询到数据，返回false
			return TaotaoResult.ok(false);
		}
		//数据可以使用
		return TaotaoResult.ok(true);
	}

	@Override
	public TaotaoResult register(TbUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaotaoResult login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
