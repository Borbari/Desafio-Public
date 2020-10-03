-- cria o banco
create database dbdesafio;
-- escolhe o bd
use dbdesafio;
-- cria a tabela
create table tbusuario(
iduser int primary key,
usuario varchar(50) not null,
login varchar(15) not null unique,
senha varchar(15) not null
);
-- mostra a tabela
describe tbusuario;
-- renomear tabela
rename table tbusuarios to tbusuario;
-- inserir dados
insert into tbusuario(iduser,usuario,login,senha)
values(1,'admin','admin','admin');

-- exibe dados
select * from tbusuario;
-- update da tabela
update tbusuario set senha='1234' where iduser=1;
-- deletar registro
delete from tbusuarios where iduser=3;

create table tbresultado(
idjogo int primary key auto_increment,
placar int(4) not null,
mintemporada int(4) not null,
maxtemporada int(4) not null,
recordemin int(4) not null,
recordemax int(4) not null
);

drop table tbresultado;
insert into tbresultado(idjogo,placar,mintemporada,maxtemporada,recordemin,recordemax)
values(1,'12','12','12','0','0'),
(2,'24','12','24','0','1'),
(3,'10','10','24','1','1'),
(4,'24','10','24','1','1');

select * from tbresultado;
select * from tbusuario;
select idjogo, sum(recordemax) as total from tbresultado group by recordemax;
select sum(recordemax) as total from tbresultado;
select sum(recordemax)from tbresultado where idjogo;
select sum(maxtemporada) as total from tbresultado;

