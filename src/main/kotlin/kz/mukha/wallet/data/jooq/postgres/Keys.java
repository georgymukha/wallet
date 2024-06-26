/*
 * This file is generated by jOOQ.
 */
package kz.mukha.wallet.data.jooq.postgres;


import kz.mukha.wallet.data.jooq.postgres.tables.TransactionItems;
import kz.mukha.wallet.data.jooq.postgres.tables.Transactions;
import kz.mukha.wallet.data.jooq.postgres.tables.UserWalletPermissions;
import kz.mukha.wallet.data.jooq.postgres.tables.Users;
import kz.mukha.wallet.data.jooq.postgres.tables.Wallets;
import kz.mukha.wallet.data.jooq.postgres.tables.records.TransactionItemsRecord;
import kz.mukha.wallet.data.jooq.postgres.tables.records.TransactionsRecord;
import kz.mukha.wallet.data.jooq.postgres.tables.records.UserWalletPermissionsRecord;
import kz.mukha.wallet.data.jooq.postgres.tables.records.UsersRecord;
import kz.mukha.wallet.data.jooq.postgres.tables.records.WalletsRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * wallet.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<TransactionItemsRecord> TRANSACTION_ITEMS_PKEY = Internal.createUniqueKey(TransactionItems.TRANSACTION_ITEMS, DSL.name("transaction_items_pkey"), new TableField[] { TransactionItems.TRANSACTION_ITEMS.TRANSACTION_ITEM_ID }, true);
    public static final UniqueKey<TransactionsRecord> TRANSACTIONS_PKEY = Internal.createUniqueKey(Transactions.TRANSACTIONS, DSL.name("transactions_pkey"), new TableField[] { Transactions.TRANSACTIONS.TRANSACTION_ID }, true);
    public static final UniqueKey<UserWalletPermissionsRecord> USER_WALLET_PERMISSIONS_PKEY = Internal.createUniqueKey(UserWalletPermissions.USER_WALLET_PERMISSIONS, DSL.name("user_wallet_permissions_pkey"), new TableField[] { UserWalletPermissions.USER_WALLET_PERMISSIONS.PERMISSION_ID }, true);
    public static final UniqueKey<UsersRecord> USERS_EMAIL_KEY = Internal.createUniqueKey(Users.USERS, DSL.name("users_email_key"), new TableField[] { Users.USERS.EMAIL }, true);
    public static final UniqueKey<UsersRecord> USERS_PKEY = Internal.createUniqueKey(Users.USERS, DSL.name("users_pkey"), new TableField[] { Users.USERS.USER_ID }, true);
    public static final UniqueKey<UsersRecord> USERS_USERNAME_KEY = Internal.createUniqueKey(Users.USERS, DSL.name("users_username_key"), new TableField[] { Users.USERS.USERNAME }, true);
    public static final UniqueKey<WalletsRecord> WALLETS_PKEY = Internal.createUniqueKey(Wallets.WALLETS, DSL.name("wallets_pkey"), new TableField[] { Wallets.WALLETS.WALLET_ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<TransactionItemsRecord, TransactionsRecord> TRANSACTION_ITEMS__TRANSACTION_ITEMS_TRANSACTION_ID_FKEY = Internal.createForeignKey(TransactionItems.TRANSACTION_ITEMS, DSL.name("transaction_items_transaction_id_fkey"), new TableField[] { TransactionItems.TRANSACTION_ITEMS.TRANSACTION_ID }, Keys.TRANSACTIONS_PKEY, new TableField[] { Transactions.TRANSACTIONS.TRANSACTION_ID }, true);
    public static final ForeignKey<TransactionsRecord, WalletsRecord> TRANSACTIONS__TRANSACTIONS_WALLET_ID_FKEY = Internal.createForeignKey(Transactions.TRANSACTIONS, DSL.name("transactions_wallet_id_fkey"), new TableField[] { Transactions.TRANSACTIONS.WALLET_ID }, Keys.WALLETS_PKEY, new TableField[] { Wallets.WALLETS.WALLET_ID }, true);
    public static final ForeignKey<UserWalletPermissionsRecord, UsersRecord> USER_WALLET_PERMISSIONS__USER_WALLET_PERMISSIONS_USER_ID_FKEY = Internal.createForeignKey(UserWalletPermissions.USER_WALLET_PERMISSIONS, DSL.name("user_wallet_permissions_user_id_fkey"), new TableField[] { UserWalletPermissions.USER_WALLET_PERMISSIONS.USER_ID }, Keys.USERS_PKEY, new TableField[] { Users.USERS.USER_ID }, true);
    public static final ForeignKey<UserWalletPermissionsRecord, WalletsRecord> USER_WALLET_PERMISSIONS__USER_WALLET_PERMISSIONS_WALLET_ID_FKEY = Internal.createForeignKey(UserWalletPermissions.USER_WALLET_PERMISSIONS, DSL.name("user_wallet_permissions_wallet_id_fkey"), new TableField[] { UserWalletPermissions.USER_WALLET_PERMISSIONS.WALLET_ID }, Keys.WALLETS_PKEY, new TableField[] { Wallets.WALLETS.WALLET_ID }, true);
    public static final ForeignKey<WalletsRecord, UsersRecord> WALLETS__WALLETS_USER_ID_FKEY = Internal.createForeignKey(Wallets.WALLETS, DSL.name("wallets_user_id_fkey"), new TableField[] { Wallets.WALLETS.OWNER_ID }, Keys.USERS_PKEY, new TableField[] { Users.USERS.USER_ID }, true);
}
