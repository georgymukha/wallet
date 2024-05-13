/*
 * This file is generated by jOOQ.
 */
package kz.mukha.wallet.data.jooq.postgres;


import kz.mukha.wallet.data.jooq.postgres.tables.TransactionItems;
import kz.mukha.wallet.data.jooq.postgres.tables.Transactions;
import kz.mukha.wallet.data.jooq.postgres.tables.UserWalletPermissions;
import kz.mukha.wallet.data.jooq.postgres.tables.Users;
import kz.mukha.wallet.data.jooq.postgres.tables.Wallets;


/**
 * Convenience access to all tables in wallet.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Tables {

    /**
     * The table <code>wallet.transaction_items</code>.
     */
    public static final TransactionItems TRANSACTION_ITEMS = TransactionItems.TRANSACTION_ITEMS;

    /**
     * The table <code>wallet.transactions</code>.
     */
    public static final Transactions TRANSACTIONS = Transactions.TRANSACTIONS;

    /**
     * The table <code>wallet.user_wallet_permissions</code>.
     */
    public static final UserWalletPermissions USER_WALLET_PERMISSIONS = UserWalletPermissions.USER_WALLET_PERMISSIONS;

    /**
     * The table <code>wallet.users</code>.
     */
    public static final Users USERS = Users.USERS;

    /**
     * The table <code>wallet.wallets</code>.
     */
    public static final Wallets WALLETS = Wallets.WALLETS;
}