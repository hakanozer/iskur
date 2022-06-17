export interface IUser {
    user: User[];
}

export interface User {
    durum:    boolean;
    mesaj:    string;
    bilgiler?: Bilgiler;
}

export interface Bilgiler {
    userId?:      string;
    userName?:    string;
    userSurname?: string;
    userEmail?:   string;
    userPhone?:   string;
    face?:        string;
    faceID?:      string;
}
