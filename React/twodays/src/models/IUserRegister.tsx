
export interface IUserRegister {
    user: UserRegister[];
}

export interface UserRegister {
    durum:       boolean;
    mesaj:       string;
    kullaniciId?: string;
}
