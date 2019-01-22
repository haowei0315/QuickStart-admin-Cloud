package com.spdata.crm.account.service;

import com.spdata.crm.account.dao.AccountDao;
import com.spdata.common.account.Account;
import com.spdata.common.base.BaseService;
import com.spdata.common.role.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangqifang
 */
@Slf4j
@Service
public class AccountService extends BaseService<AccountDao, Account> {
    @Autowired
    private AccountDao accountDao;

    /**
     * 修改密码
     *
     * @param account
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePassword(Account account) {
        return accountDao.updatePassword(account);
    }

    /**
     * 通过username查询账户信息
     *
     * @param username
     * @return
     */
    public Account findAccount(String username) {
        return accountDao.findAccount(username);
    }

    /**
     * 添加账户信息=>1.保存基础信息 2.保存账户余角色的关联关系
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(Account entity) {
        try {
            accountDao.save(entity);
            accountDao.saveRole(entity.getId(), entity.getRoles());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改头像
     *
     * @param avatar:头像
     * @param username:账户名
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAvatar(String avatar, String username) {
        return accountDao.updateAvatar(avatar, username);
    }

    /**
     * 修改用户信息
     *
     * @param account
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAccount(Account account) {
        List<Role> roles = account.getRoles();
        boolean falg = false;
        falg = accountDao.update(account);
        if (roles != null) {
            //删除角色用户关系
            falg = accountDao.delectAccountRoleRelation(account.getId());
            //保存角色用户关系
            falg = accountDao.saveRole(account.getId(), account.getRoles());
        }
        return falg;
    }
}