package org.collectionspace.services.account;


import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public AccountsCommon createAccountsCommon() {
        return new AccountsCommon();
    }

    public AccountTenant createAccountTenant() {
        return new AccountTenant();
    }

    public RoleList createRoleList() {
        return new RoleList();
    }

    public TenantsList createTenantsList() {
        return new TenantsList();
    }

    public TenantListItem createTenantListItem() {
        return new TenantListItem();
    }

    public AccountsCommonList createAccountsCommonList() {
        return new AccountsCommonList();
    }

    public AccountListItem createAccountListItem() {
        return new AccountListItem();
    }

    public Tenant createTenant() {
        return new Tenant();
    }

    public RoleValue createRoleValue() {
        return new RoleValue();
    }

}
