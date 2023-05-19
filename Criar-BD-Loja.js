db = connect( 'mongodb://localhost/Loja' );

db.dropDatabase()

db.Produto.insertMany( [
  {
    idProduto: 1,
    nome: 'Celular Galaxy S10',
    descricao: '128 Gb, Preto, com carregador',
    valor: 1250.00,
    estado: 'Poucos riscos, estado de novo'
   },
  {
    idProduto: 2,
    nome: 'Prod2',
    descricao: 'Bla bla',
    valor: 1100.00,
    estado: 'Bla bla'
   },
  {
    idProduto: 3,
    nome: 'Prod3',
    descricao: 'Bla bla',
    valor: 120.00,
    estado: 'Bla bla'
   },
  {
    idProduto: 4,
    nome: 'Prod4',
    descricao: 'Bla bla',
    valor: 1300.00,
    estado: 'Bla bla'
   },
  {
    idProduto: 5,
    nome: 'Prod5',
    descricao: 'Bla bla',
    valor: 9400.00,
    estado: 'Bla bla'
   },
  {
    idProduto: 6,
    nome: 'Prod6',
    descricao: 'Bla bla',
    valor: 1600.00,
    estado: 'Bla bla'
   },
] )
