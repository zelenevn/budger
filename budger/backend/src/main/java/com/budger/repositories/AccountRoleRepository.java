package com.budger.repositories;

import com.budger.data.entities.AccountRole;
import com.budger.data.entities.pk.AccountRoleId;
import org.springframework.data.repository.CrudRepository;

public interface AccountRoleRepository extends CrudRepository<AccountRole, AccountRoleId> {
}