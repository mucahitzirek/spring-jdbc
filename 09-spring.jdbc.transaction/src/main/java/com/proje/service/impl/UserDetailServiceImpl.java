package com.proje.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proje.model.UserDetail;
import com.proje.repository.UserDetailRepository;
import com.proje.service.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	private UserDetailRepository userDetailServiceImpl;

	@Override
	public boolean save(int userId, UserDetail userDetail) {

		return this.userDetailServiceImpl.save(userId, userDetail);
	}

	@Override
	public boolean update(UserDetail userDetail) {

		return this.userDetailServiceImpl.update(userDetail);
	}

	@Override
	public UserDetail findById(int id) {

		return this.userDetailServiceImpl.findById(id);
	}

	@Override
	public List<UserDetail> findUserDetails() {

		return this.userDetailServiceImpl.findUserDetails();
	}

}
