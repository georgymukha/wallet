/*
 * This file is generated by jOOQ.
 */
package kz.mukha.wallet.data.jooq.postgres.tables.records;


import java.time.LocalDateTime;
import java.util.UUID;

import kz.mukha.wallet.data.jooq.postgres.tables.UserWalletPermissions;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class UserWalletPermissionsRecord extends UpdatableRecordImpl<UserWalletPermissionsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>wallet.user_wallet_permissions.permission_id</code>.
     */
    public void setPermissionId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>wallet.user_wallet_permissions.permission_id</code>.
     */
    public UUID getPermissionId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>wallet.user_wallet_permissions.user_id</code>.
     */
    public void setUserId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>wallet.user_wallet_permissions.user_id</code>.
     */
    public UUID getUserId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>wallet.user_wallet_permissions.wallet_id</code>.
     */
    public void setWalletId(UUID value) {
        set(2, value);
    }

    /**
     * Getter for <code>wallet.user_wallet_permissions.wallet_id</code>.
     */
    public UUID getWalletId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>wallet.user_wallet_permissions.permission_type</code>.
     */
    public void setPermissionType(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>wallet.user_wallet_permissions.permission_type</code>.
     */
    public String getPermissionType() {
        return (String) get(3);
    }

    /**
     * Setter for <code>wallet.user_wallet_permissions.created_at</code>.
     */
    public void setCreatedAt(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>wallet.user_wallet_permissions.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>wallet.user_wallet_permissions.updated_at</code>.
     */
    public void setUpdatedAt(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>wallet.user_wallet_permissions.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserWalletPermissionsRecord
     */
    public UserWalletPermissionsRecord() {
        super(UserWalletPermissions.USER_WALLET_PERMISSIONS);
    }

    /**
     * Create a detached, initialised UserWalletPermissionsRecord
     */
    public UserWalletPermissionsRecord(UUID permissionId, UUID userId, UUID walletId, String permissionType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(UserWalletPermissions.USER_WALLET_PERMISSIONS);

        setPermissionId(permissionId);
        setUserId(userId);
        setWalletId(walletId);
        setPermissionType(permissionType);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        resetChangedOnNotNull();
    }
}
