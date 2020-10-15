

function Factorial(n: int): int
  requires n >= 0
  decreases n
{
  if n == 0 then 1 else n * Factorial(n-1)
}
 
method LoopyFactorial(n: int) returns (u: int)
  requires n >= 0
  ensures u == Factorial(n)
{
  u := 1;
  var r := 0;
  while (r < n) 
    invariant u == Factorial(r) && r <= n
    decreases n - r // FILL IN YOUR INVARIANT HERE
  {
    var v := u;
    var s := 1;
    while (s <= r) 
      invariant u == s*v && s <= r+1
      decreases r - s // FILL IN YOUR INVARIANT HERE
    {
      u := u + v;
      s := s + 1;
    }
    r := r + 1;
    assert u == r*v == Factorial(r); // FILL IN YOUR ASSERTION HERE 
  }
}