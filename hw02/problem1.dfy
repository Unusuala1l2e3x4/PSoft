

function exp(m: int, n: int): int
  requires n >= 0
  ensures n >= 0
  decreases n
{
  if(n == 0)
    then 1
  else
    m * exp(m, n - 1)
}


lemma square_and_halve(m:int, n:int)
  requires n >= 0
  ensures exp(m, 2 * n) == exp(m * m, n)
{
  if (n != 0)
  {
    square_and_halve(m, n - 1);
  }
}

// Precondition: n >= 0
// Postcondition: result = m^n
method power(m: int, n: int) returns (result: int)
  requires n >= 0
  ensures result == exp(m, n)
{ 
  var x := m;
  var y := n;
  result := 1;
  while y != 0
    invariant y >= 0
    invariant result * exp(x, y) == exp(m, n)
    decreases y
  { 
    if y % 2 == 0 {
      square_and_halve(x, y / 2);
      x := x*x;
      y := y/2;
    } else {
      result := result*x;
      y := y-1;
    }
  }
}


// method Main() {
//   var result := power(20,6);
//   print "power(20,6) = ", result, "\n";
//   result := power(20,6);
//   print "power(20,6) = ", result, "\n";
// }





 
